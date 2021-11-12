package Presentacion.Controller;

public class Context {

	private Eventos event;
	private Object data;
	
	public Context(Eventos event, Object data) {
		this.event = event;
		this.data = data;
	}

	public Eventos getEvent() {
		return event;
	}

	public void setEvent(Eventos event) {
		this.event = event;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getDataObject() {
		return data;
	}
}
