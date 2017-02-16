import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by midori on 2017/02/16.
 */
public class Train {
	private String id;
	private int num;
	private String name;
	private List<Integer> courses = Lists.newArrayList();
	private List<Double> distances = Lists.newArrayList();

	public Train(String id) {
		this.id = id;
	}

	public Train(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Train appendCourse(Integer course) {
		courses.add(course);
		return this;
	}

	public Train appendDistance(Double distance) {
		distances.add(distance);
		return this;
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

	public List<Integer> getCourses() {
		return courses;
	}

	public void setCourses(List<Integer> courses) {
		this.courses = courses;
	}

	public List<Double> getDistances() {
		return distances;
	}

	public void setDistances(List<Double> distances) {
		this.distances = distances;
	}

	@Override
	public String toString() {
		return "Train{" +
				"name='" + name + '\'' +
				'}';
	}
}
