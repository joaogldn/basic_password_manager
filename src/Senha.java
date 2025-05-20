public class Senha {
    private String servico;
    private String email;
    private String senhaCriptografada;

    public Senha(String servico, String email, String senhaCriptografada) {
        this.servico = servico;
        this.email = email;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getServico() {
        return servico;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }
}
