package gals;

public class SemanticError extends AnalysisError
{
    public SemanticError(String msg, int position, String symbol)
    {
        super(msg, position, symbol);
    }

    public SemanticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }
}
