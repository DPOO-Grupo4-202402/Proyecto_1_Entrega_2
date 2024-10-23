package logica;

public class Resenia {
	
	private int rating;
	private String comentario;
	
	public Resenia(int rating, String comentario) {
		super();
		this.rating = rating;
		this.comentario = comentario;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
