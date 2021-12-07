package gals;

public class ClasseId {

    final static String PALAVRA_RESERVADA     = "palavra reservada    ";
    final static String IDENTIFICADOR         = "identificador        ";
    final static String CONSTANTE_INTEIRA     = "constante inteira    ";
    final static String CONSTANTE_REAL        = "constante real       ";
    final static String CONSTANTE_BINARIA     = "constante binária    ";
    final static String CONSTANTE_HEXADECIMAL = "constante hexadecimal";
    final static String CONSTANTE_STRING      = "constante string     ";
    final static String SIMBOLO_ESPECIAL      = "símbolo especial     ";
    
    public ClasseId() {
    }

    public static String getClasse(int id) {
                
        if (id == 2) {
            return IDENTIFICADOR;
        } else if (id == 3) {
            return CONSTANTE_INTEIRA;
        } else if (id == 4) {
            return CONSTANTE_REAL;
        } else if (id == 5) {
            return CONSTANTE_BINARIA;
        } else if (id == 6) {
            return CONSTANTE_HEXADECIMAL;
        } else if (id == 7) {
            return CONSTANTE_STRING;
        } else if (id >= 8 && id <= 28) {
            return PALAVRA_RESERVADA;
        } else if (id >= 29 && id <= 48) {
            return SIMBOLO_ESPECIAL;
        }
        
        return "";
    }

}
