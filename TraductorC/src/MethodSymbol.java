
public class MethodSymbol extends Symbol implements Scope {

	public MethodSymbol(String name, Type type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	public MethodSymbol(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getScopeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scope getEnclosingScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void define(Symbol sym) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Symbol resolve(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
