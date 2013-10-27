package br.com.caiosousa.security.client;

//TODO - Fazer todas estas configurações com JNDI
public class Propriedades {
	
	public String getEnderecoLogin() {
		return "http://localhost:8080/modulosecurity/login";
	}
	
	public String getEnderecoAtivaSessaoPendente() {
		return "http://localhost:8080/modulosecurity/ativaSessao";
	}
	
	public String getEnderecoValidaSessao(final String token) {
		return "http://localhost:8080/modulosecurity/validaSessao?token=" + token;
	}
	
}
