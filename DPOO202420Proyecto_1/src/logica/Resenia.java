package logica;

import java.io.Serializable;
import java.util.Date;

public class Resenia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario autor;
	private int rating;
	private String comentario;
	private Date fecha;
	
	public Resenia(int rating, String comentario, Usuario autor, Date fecha) {;
		this.rating = rating;
		this.comentario = comentario;
		this.setAutor(autor);
		this.setFecha(fecha);
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		if (rating <= 0 || rating >= 5) {
			throw new IllegalArgumentException("La puntuacion debe estar entre 1 y 5.");
		}
		this.rating = rating;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String obtenerResenia() {
		return "Autor: " + autor.getNombre()  + "Fecha: " + fecha.toString() + "\n" + "Rating: " + rating + "\n" + "Comentario: " + comentario;
	}
}
