package demo.pojo;

import java.io.Serializable;
import java.util.List;

public class TbScenery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7565958757258792992L;
	private Integer id;
	private String jdname;

	private List<TbSceneryTickets> tbSceneryTickets;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJdname() {
		return jdname;
	}

	public void setJdname(String jdname) {
		this.jdname = jdname;
	}

	public List<TbSceneryTickets> getTbSceneryTickets() {
		return tbSceneryTickets;
	}

	public void setTbSceneryTickets(List<TbSceneryTickets> tbSceneryTickets) {
		this.tbSceneryTickets = tbSceneryTickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jdname == null) ? 0 : jdname.hashCode());
		result = prime * result + ((tbSceneryTickets == null) ? 0 : tbSceneryTickets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbScenery other = (TbScenery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jdname == null) {
			if (other.jdname != null)
				return false;
		} else if (!jdname.equals(other.jdname))
			return false;
		if (tbSceneryTickets == null) {
			if (other.tbSceneryTickets != null)
				return false;
		} else if (!tbSceneryTickets.equals(other.tbSceneryTickets))
			return false;
		return true;
	}

}
