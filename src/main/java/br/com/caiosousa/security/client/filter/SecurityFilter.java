package br.com.caiosousa.security.client.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.caiosousa.exception.AcessoNegadoExeption;
import br.com.caiosousa.security.client.Cliente;
import br.com.caiosousa.security.vo.Sessao;

@Component
public class SecurityFilter implements javax.servlet.Filter {

	@Autowired
	Cliente cliente;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			validaSessao(httpRequest);
			chain.doFilter(request, response);
		} catch (AcessoNegadoExeption ex) {
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
		}
		
	}

	private void validaSessao(final HttpServletRequest httpRequest) throws AcessoNegadoExeption {
		try {
			final String token = httpRequest.getHeader("caiosousa.token");
			final Sessao sessao = cliente.validaSessao(token);
			if (sessao == null) {
				throw AcessoNegadoExeption.INSTANCE;
			}
		} catch (Exception ex) {
			throw AcessoNegadoExeption.INSTANCE;
		}
	}
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
	
}
