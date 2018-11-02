package demo.pojo;

import java.io.Serializable;

public class TbSceneryTickets implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1853495884891977512L;
	private Integer id;
	private Integer sceneryId;// Íâ¼ü
	private String typeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSceneryId() {
		return sceneryId;
	}

	public void setSceneryId(Integer sceneryId) {
		this.sceneryId = sceneryId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sceneryId == null) ? 0 : sceneryId.hashCode());
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		TbSceneryTickets other = (TbSceneryTickets) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sceneryId == null) {
			if (other.sceneryId != null)
				return false;
		} else if (!sceneryId.equals(other.sceneryId))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

}
