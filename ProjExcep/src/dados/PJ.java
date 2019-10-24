package dados;

public class PJ extends Pessoa {

    private String cnpj;

    @Override
    public String getId() {
        return cnpj;
    }

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}