public class Usuario {
    private String usuario;
    private String senhaCriptografada;

    public Usuario(String usuario, String senhaCriptografada) {
        this.usuario = usuario;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }
}
