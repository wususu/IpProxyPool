package entity;

public class Cache<T> {
	
	private int id;
																																																																																													
	private T value;
	
	private Integer weight;
	
	private boolean quit;
	
	private boolean checked;
	
	public Cache() {
		// TODO Auto-generated constructor stub
	}
	
	public Cache(T value) {
		// TODO Auto-generated constructor stub
		this.setId(id);
		this.value = value;
		this.setQuit(false);
		this.checked = false;
		this.weight = 0;
	}

	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
