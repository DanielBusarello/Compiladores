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

    public void executeAction(int action, Token token) throws SemanticError
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
            case 7: acao7();
                break;
            case 11: acao11();
                break;
            case 12: acao12();
                break;
            case 14: acao14();
                break;
            case 15: acao15();
                break;
            case 16: acao16();
                break;
            case 17: acao17(token);
                break;
            case 24: acao24(token);
                break;
            default: throw new SemanticError("Ação semântica não implementada: " + action);
        }

        System.out.println(codigo.toString());
        //saveFile(codigo.toString());
    }

    private void acao1() throws SemanticError {
        String tipo1 = pilhaTipos.pop();
        String tipo2 = pilhaTipos.pop();

        if (isFloat(tipo1) && isFloat(tipo2) || isInteger(tipo1) && isInteger(tipo2) ||
                isFloat(tipo1) && isInteger(tipo2) || isInteger(tipo1) && isFloat(tipo2)) {

            if (isFloat(tipo1) || isFloat(tipo2)) {
                pilhaTipos.push(FLOAT);
            } else
                pilhaTipos.push(INTEGER);

            codigo.add(ADD);
        } else {
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
        }
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
        codigo.add("\t"+ LDCI8 + " " + token.getLexeme() + "\n");
        codigo.add("\t"+ CONVR8 +"\n");
    }

    private void acao6(Token token) {
        pilhaTipos.push(FLOAT);
        codigo.add("\t"+ LDCR8 + " " + token.getLexeme() + "\n");
    }

    private void acao7() throws SemanticError {
        String tipo = pilhaTipos.pop();
        if (isFloat(tipo) || isInteger(tipo)) {
            pilhaTipos.push(tipo);
        } else
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
    }

    private void acao11() {
        pilhaTipos.push(BOOL);
        codigo.add("ldc.i4.1\n");
    }

    private void acao12() {
        pilhaTipos.push(BOOL);
        codigo.add("ldc.i4.0\n");
    }

    private void acao14() {
        String tipo = pilhaTipos.pop();
        if (isInteger(tipo)) {
            codigo.add("\t" + CONVI8);
        }
        codigo.add("\tcall void [mscorlib]System.Console::Write(" + tipo + ")\n");
    }

    private void acao15() {
        codigo.add("" +
                ".assembly extern mscorlib {}\n" +
                ".assembly _codigo_objeto{}\n" +
                ".module   _codigo_objeto.exe\n\n" +
                ".class public _UNICA{\n\n" +
                ".method static public void _principal() {\n" +
                "\t.entrypoint\n"
        );
    }

    private void acao16() {
        codigo.add("" +
                "\tret\n" +
                "\t}\n" +
                "}");
    }

    private void acao17(Token token) {
        pilhaTipos.push(STRING);
        codigo.add("\t"+ LDSTR + " " + token.getLexeme() + "\n");
    }

    public void acao24(Token token) {
        listaId.add(token.getLexeme());
   }
}
