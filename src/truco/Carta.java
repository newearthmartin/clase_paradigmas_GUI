package truco;

public class Carta {
    enum Palo {
        ESPADAS,
        OROS,
        BASTOS,
        COPAS
    }

    int numero;
    Palo palo;

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }
}
