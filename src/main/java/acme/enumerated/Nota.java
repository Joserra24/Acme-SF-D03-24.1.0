
package acme.enumerated;

public enum Nota {

	A_Plus("A+"), A("A"), B("B"), C("C"), F("F"), F_Minus("F-");


	private final String nota;


	Nota(final String nota) {
		this.nota = nota;
	}

	public String getNota() {
		return this.nota;
	}
}
