package demo.free;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTest {

	public static void main(String[] args) throws IOException, TemplateException {
		// 1.设置配置类
		Configuration cfg = new Configuration(Configuration.getVersion());
		//2. 设置模板所在的目录
		URL resource = FreeMarkerTest.class.getResource("/");
		cfg.setDirectoryForTemplateLoading(new File(resource.getPath()));
		//3.设置字符集
		cfg.setDefaultEncoding("utf-8");
		//4.加载模板
		Template tep = cfg.getTemplate("test.ftl");
		//5.创建数据模型
		Map<String,String>map=new HashMap<>();
		map.put("name","翁启飞");
		map.put("message","我是你的迷妹");
		//6.创建Writer对象
		FileWriter writer=new FileWriter(new File("test.html"));
		//7.输出数据模型到文件中
		tep.process(map,writer);
		//8.关闭Writer对象
		writer.close();
	}
}
