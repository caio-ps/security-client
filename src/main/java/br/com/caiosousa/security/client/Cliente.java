package br.com.caiosousa.security.client;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.caiosousa.exception.AcessoNegadoExeption;
import br.com.caiosousa.security.vo.Login;
import br.com.caiosousa.security.vo.LoginsDisponiveisJSON;
import br.com.caiosousa.security.vo.Sessao;

@Component
public class Cliente {

	@Autowired
	RestTemplate rest;
	@Autowired
	Propriedades propriedades;
	
	public List<Sessao> login(final String email, final String senha) throws AcessoNegadoExeption {
		
		try {
		
			final Login login = new Login(email, senha);
			
			final LoginsDisponiveisJSON logins =
					rest.postForObject(propriedades.getEnderecoLogin(), login, LoginsDisponiveisJSON.class);
	        
			if (logins == null || logins.getLoginsDisponiveis() == null || logins.getLoginsDisponiveis().isEmpty()) {
				throw AcessoNegadoExeption.INSTANCE;
			}
			
			return logins.getLoginsDisponiveis();
			
		} catch (Exception ex) {
			throw AcessoNegadoExeption.INSTANCE;
		}
        
	}
	
	public Sessao ativaSessaoPendente(final String token) throws AcessoNegadoExeption {
		
		try {
		
			final Sessao sessao =
					rest.postForObject(propriedades.getEnderecoAtivaSessaoPendente(), token, Sessao.class);
	        
			if (sessao == null) {
				throw AcessoNegadoExeption.INSTANCE;
			}
			
			return sessao;
			
		} catch (Exception ex) {
			throw AcessoNegadoExeption.INSTANCE;
		}
        
	}
	
	public Sessao validaSessao(final String token) throws AcessoNegadoExeption {
		
		try {
		
			final URI url = new URI(propriedades.getEnderecoValidaSessao(token)); 
			final Sessao sessao = rest.getForObject(url, Sessao.class);
	        
			if (sessao == null) {
				throw AcessoNegadoExeption.INSTANCE;
			}
			
			return sessao;
			
		} catch (Exception ex) {
			throw AcessoNegadoExeption.INSTANCE;
		}
        
	}
}