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
            case 8: acao8();
                break;
            case 9: acao9(token);
                break;
            case 10: acao10();
                break;
            case 11: acao11();
                break;
            case 12: acao12();
                break;
            case 13: acao13();
                break;
            case 14: acao14();
                break;
            case 15: acao15();
                break;
            case 16: acao16();
                break;
            case 17: acao17(token);
                break;
            case 18: acao18(token);
                break;
            case 19: acao19(token);
                break;
            case 20: acao20(token);
                break;
            case 21: acao21();
                break;
            case 22: acao22();
                break;
            case 23: acao23();
                break;
            case 24: acao24(token);
                break;
            case 25: acao25(token);
                break;
            case 27: acao27();
                break;
            case 34: acao34(token);
                break;
            default: throw new SemanticError("Ação semântica não implementada: " + action);
        }

        System.out.println(codigo.toString());
        //saveFile(codigo.toString());
    }

    private boolean verifyNumTypes() throws SemanticError {
        String tipo1 = pilhaTipos.pop();
        String tipo2 = pilhaTipos.pop();

        if (isFloat(tipo1) && isFloat(tipo2) || isInteger(tipo1) && isInteger(tipo2) ||
                isFloat(tipo1) && isInteger(tipo2) || isInteger(tipo1) && isFloat(tipo2)) {

            if (isFloat(tipo1) || isFloat(tipo2)) {
                pilhaTipos.push(FLOAT);
            } else
                pilhaTipos.push(INTEGER);

            return true;

        }  else {
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
        }
    }

    private boolean verifyLogicTypes() throws SemanticError {
        String tipo1 = pilhaTipos.pop();
        String tipo2 = pilhaTipos.pop();

        if (tipo1.equals(BOOL) && tipo2.equals(BOOL)) {
            pilhaTipos.push(BOOL);
            return true;
        } else throw new SemanticError("tipo(s) incompatível(is) em expressão lógica");
    }

    private void acao1() throws SemanticError {
        if (verifyNumTypes())
            codigo.add("\t" + ADD + "\n");
    }

    private void acao2() throws SemanticError {
        if (verifyNumTypes())
            codigo.add("\t" + SUB + "\n");
    }

    private void acao3() throws SemanticError {
        if (verifyNumTypes())
            codigo.add("\t" + MUL + "\n");
    }

    private void acao4() throws SemanticError {
        if (verifyNumTypes())
            codigo.add("\t" + DIV + "\n");
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

    private void acao8() throws SemanticError {
        String tipo = pilhaTipos.pop();
        if (isFloat(tipo) || isInteger(tipo)) {
            pilhaTipos.push(tipo);

            codigo.add("\t" + LDCI8 + " -1\n");
            codigo.add("\t" + CONVR8 + "\n");
            codigo.add("\t" + MUL + "\n");
        } else
            throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética");
    }

    private void acao9(Token token) {
        operador = token.getLexeme();
    }

    private void acao10() throws SemanticError {
        String tipo1 = pilhaTipos.pop();
        String tipo2 = pilhaTipos.pop();

        if (tipo1.equals(tipo2)) {
            pilhaTipos.push(BOOL);
        } else throw new SemanticError("tipo(s) incompatível(is) em expressão relacional");

        switch(operador) {
            case ">": codigo.add("\tcgt\n");
            case "<": codigo.add("\tclt\n");
            case "==": codigo.add("\tceq\n");
            case "<>": codigo.add("\tceq\n" +
                                    "\tldc.i4.0\n" +
                                    "\tceq\n");
        }
    }

    private void acao11() {
        pilhaTipos.push(BOOL);
        codigo.add("\tldc.i4.1\n");
    }

    private void acao12() {
        pilhaTipos.push(BOOL);
        codigo.add("\tldc.i4.0\n");
    }

    private void acao13() throws SemanticError {
        String tipo = pilhaTipos.pop();
        if (tipo.equals(BOOL)) {
            pilhaTipos.push(BOOL);

            codigo.add("\tldc.i4.1\n");
            codigo.add("\txor\n");
        } else throw new SemanticError("tipo(s) incompatível(is) em expressão lógica");
    }

    private void acao14() {
        String tipo = pilhaTipos.pop();
        if (isInteger(tipo)) {
            codigo.add("\t" + CONVI8 + "\n");
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
        codigo.add("\t" + LDSTR + " " + token.getLexeme() + "\n");
    }

    private void acao18(Token token) {
        pilhaTipos.push(STRING); // newLine
        codigo.add("\t" + LDSTR + " \"\\n\"" + "\n");
    }

    private void acao19(Token token) {
        pilhaTipos.push(STRING);  // space
        codigo.add("\t" + LDSTR + " \" \"" + "\n");
    }

    private void acao20(Token token) {
        pilhaTipos.push(STRING); // tab
        codigo.add("\t" + LDSTR + " \"\\t\"" + "\n");
    }

    private void acao21() throws SemanticError {
        if (verifyLogicTypes())
            codigo.add("\t" + AND + "\n");
    }

    private void acao22() throws SemanticError {
        if (verifyLogicTypes())
            codigo.add("\t" + OR + "\n");
    }

    private void acao23() {
        for (String id : listaId) {
            codigo.add("\t.locals ("+ getTypeByPrefix(id) + " " + id + ")\n");
        }

        listaId.clear();
    }

    private void acao24(Token token) {
        listaId.add(token.getLexeme());
   }

    private void acao25(Token token) {
        //String lexeme = token.getLexeme();
        String lexeme = listaId.get(0);

        listaId.remove(lexeme);

        if (getTypeByPrefix(lexeme).equals(INTEGER))
            codigo.add("\t" + CONVR8 + "\n");

        codigo.add("\tstloc " + lexeme + "\n");
    }

    private void acao27() {
        for (String id : listaId) {
            codigo.add("\tcall string [mscorlib]System.Console::ReadLine()\n");

            if (getTypeByPrefix(id).equals(INTEGER)) {
                codigo.add("\tcall int64 [mscorlib]System.Int64::Parse(string)\n");
            }

            codigo.add("\tstloc " + id + "\n");
        }

        listaId.clear();
    }

    private void acao31() {
        for (String id : listaId) {
            codigo.add("\t.locals"+ getTypeByPrefix(id) + "\n");
        }

        listaId.clear();
    }

    private void acao32() {
        for (String id : listaId) {
            codigo.add("\t.locals"+ getTypeByPrefix(id) + "\n");
        }

        listaId.clear();
    }

    private void acao33() {
        for (String id : listaId) {
            codigo.add("\t.locals"+ getTypeByPrefix(id) + "\n");
        }

        listaId.clear();
    }

    private void acao34(Token token) {
        String lexeme = token.getLexeme();

        codigo.add("\tldloc " + lexeme + "\n");
        pilhaTipos.add(getTypeByPrefix(lexeme));

        if (getTypeByPrefix(lexeme).equals(INTEGER)) {
            codigo.add("\t" + CONVR8 + "\n");
        }
    }


}
