package dados;

public class PF extends Pessoa {
    private String cpf;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getId() {
        return cpf;
    }

}