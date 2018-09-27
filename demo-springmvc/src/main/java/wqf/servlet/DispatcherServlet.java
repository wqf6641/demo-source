package wqf.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wqf.anntation.Autowired;
import wqf.anntation.Controller;
import wqf.anntation.RequestMapping;
import wqf.anntation.RequestParam;
import wqf.anntation.Service;

public class DispatcherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6004667290708067246L;
	List<String> packageNames = new ArrayList<String>();
	// 所有类的实例，key是注解的value,value是所有类的实例
	Map<String, Object> instanceMap = new HashMap<String, Object>();
	Map<String, Object> handerMap = new HashMap<String, Object>();
	Properties prop = new Properties();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化配置
		initParam(config);

		scanPackage(prop.getProperty("scanPackage"));
		try {
			filterAndInstance();// 实例化
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 建立映射关系
		handerMap();
		// 实现注入
		ioc();
	}

	private void initParam(ServletConfig config) {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(config.getServletContext().getInitParameter("contextConfigLocation"));
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ioc() {
		if (instanceMap.isEmpty())
			return;
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
			// 拿到里面的所有属性
			Field fields[] = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);// 可访问私有属性
				if (!field.isAnnotationPresent(Autowired.class)) {
					continue;
				}
				Autowired auto = field.getAnnotation(Autowired.class);
				String value = auto.value();
				field.setAccessible(true);
				try {
					field.set(entry.getValue(), instanceMap.get(value));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 建立映射关系
	 */
	private void handerMap() {
		if (instanceMap.size() <= 0) {
			return;
		}
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
			Class<?> instance = entry.getValue().getClass();// 拿到实例化对象
			if (instance.isAnnotationPresent(Controller.class)) {
				Controller controller = (Controller) instance.getAnnotation(Controller.class);// 根据找到实例化类
				String conPath = controller.value();// 找到注解值-路径/wqf
				RequestMapping reqMap = instance.getAnnotation(RequestMapping.class);
				String conReqPath = "";
				if (reqMap != null) {
					conReqPath = reqMap.value();// demo
				}
				Method[] methods = instance.getMethods();// 类中的方法
				for (Method method : methods) {
					if (method.isAnnotationPresent(RequestMapping.class)) {
						RequestMapping rm = (RequestMapping) method.getAnnotation(RequestMapping.class);
						String rmvalue = "";
						if (rm != null) {
							rmvalue = rm.value();// query
						}
						handerMap.put("/" + conPath + conReqPath + rmvalue, method);
					} else {
						continue;
					}
				}
			} else {
				continue;
			}

		}
	}

	private void filterAndInstance() throws Exception {
		if (packageNames.size() <= 0) {
			return;
		}
		for (String className : packageNames) {
			Class<?> cName = Class.forName(className.replace(".class", "").trim());
			if (cName.isAnnotationPresent(Controller.class)) {
				Object instance = cName.newInstance();// 实例化
				Controller controller = (Controller) cName.getAnnotation(Controller.class);
				String key = controller.value();// root
				instanceMap.put(key, instance);
			} else if (cName.isAnnotationPresent(Service.class)) {
				Object instance = cName.newInstance();
				Service service = (Service) cName.getAnnotation(Service.class);
				String key = service.value();// demoServiceImpl
				if (key.equals("")) {
					key = lowerFristCase(cName.getSimpleName());
				}
				instanceMap.put(key, instance);
			} else {
				continue;
			}
		}
	}

	private String lowerFristCase(String simpleName) {
		char[] charArray = simpleName.toCharArray();
		charArray[0] += 32;// 取巧，只适合首字母是大写的 才可以转换为小写
		return String.valueOf(charArray);
	}

	/**
	 * 扫描包下的所有文件
	 *
	 * @param packageStr
	 */
	private void scanPackage(String packageStr) {
		URL url = this.getClass().getClassLoader()
				.getResource("/" + packageStr.replaceAll("\\.", "/").replaceAll("/+", "/"));
		String pathFile = url.getFile();
		File file = new File(pathFile);
		String fileList[] = file.list();
		for (String path : fileList) {
			File eachFile = new File(pathFile + "/" + path);
			if (eachFile.isDirectory()) {
				// 递归
				scanPackage(packageStr + "." + eachFile.getName());
			} else {
				packageNames.add(packageStr + "." + eachFile.getName());// .class文件
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();// 请求路径
		String context = req.getContextPath();// 项目路径
		String path = url.replace(context, "");
		if (!this.handerMap.containsKey(url)) {
			resp.getWriter().write("<h1>404 NOT FOUND!<h1>");
			return;
		}
		Method method = (Method) handerMap.get(path);
		Object isntance = instanceMap.get(path.split("/")[1]);
		try {
			// 解析参数
			Object args[] = hand(req, resp, method);
			method.invoke(isntance, args);
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("500 error:" + Arrays.toString(e.getStackTrace()));
		}
	}

	private Object[] hand(HttpServletRequest req, HttpServletResponse resp, Method method) {
		// 获取当前执行的方法中有哪些注解的参数
		Class<?>[] parameterClazzs = method.getParameterTypes();
		Object args[] = new Object[parameterClazzs.length];
		int arg_i = 0;
		int index = 0;
		for (Class<?> parameterClazz : parameterClazzs) {
			if (ServletRequest.class.isAssignableFrom(parameterClazz)) {
				args[arg_i++] = req;
			}
			if (ServletResponse.class.isAssignableFrom(parameterClazz)) {
				args[arg_i++] = resp;
			}
			Annotation[] paramAns = method.getParameterAnnotations()[index];
			if (paramAns.length > 0) {
				for (Annotation paramAn : paramAns) {
					if (RequestParam.class.isAssignableFrom(paramAn.getClass())) {
						RequestParam rp = (RequestParam) paramAn;
						// 找到注解的name和age
						args[arg_i++] = req.getParameter(rp.value());
					}
				}
			}
			index++;
		}
		return args;
	}

}
