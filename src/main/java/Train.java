/**
 * Created by midori on 2017/02/16.
 */
public class Train {
	private String id;
	private int num;
	private String name;

	public Train(String id) {
		this.id = id;
	}

	public Train(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public boolean hasName() {
		return name != null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Train{" +
				"name='" + name + '\'' +
				'}';
	}
}
