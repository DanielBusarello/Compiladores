package gals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import static util.utils.*;

public class Semantico implements Constants
{
    private String operador = "";
    private ArrayList<String> codigo = new ArrayList<>();
    private ArrayDeque<String> pilhaTipos = new ArrayDeque<>(); //int64 float64 string bool
    private ArrayList<String> listaId = new ArrayList<>();
    private ArrayDeque<String> pilhaRotulos = new ArrayDeque<>();

    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("Ação #"+action+", Token: "+token);
        switch (action) {
            case 1: acao1();
                break;
            case 2: acao2();
                break;
            case 3: acao3();
                break;
            case 4: acao4();
                break;
            case 5: acao5(token);
                break;
            case 6: acao6(token);
                break;
            default: throw new SemanticError("Ação semântica não implementada: " + action);
        }
    }

    private void acao1() {
        String tipo1 = pilhaTipos.pop();
        String tipo2 = pilhaTipos.pop();

        if (isFloat(tipo1) || isFloat(tipo2)) {
            pilhaTipos.push(FLOAT);
        } else {
            pilhaTipos.push(INTEGER);
        }

        codigo.add(ADD);
    }

    private void acao2() {
        pilhaTipos.pop();
        pilhaTipos.pop();
        pilhaTipos.push(FLOAT);
    }

    private void acao3() {
        pilhaTipos.push(INTEGER);
    }

    private void acao4() {
        pilhaTipos.push(FLOAT);
    }

    private void acao5(Token token) {
        pilhaTipos.push(INTEGER);
        codigo.add(LDCI8 + " " +token.getLexeme() + "\n");
        codigo.add(CONVR8 +"\n");
    }

    private void acao6(Token token) {
        pilhaTipos.push(FLOAT);
        codigo.add(LDCR8 + " " + token.getLexeme() + "\n");
    }
}
