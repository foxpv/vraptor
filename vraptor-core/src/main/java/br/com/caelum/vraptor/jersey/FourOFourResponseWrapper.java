package br.com.caelum.vraptor.jersey;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * A response wrapper that does not delegate 404s. It will save its state
 * instead.
 * 
 * @author guilherme silveira
 */
class FourOFourResponseWrapper extends HttpServletResponseWrapper {
	private final HttpServletRequest req;

	FourOFourResponseWrapper(HttpServletRequest req,
			HttpServletResponse response) {
		super(response);
		this.req = req;
	}

	public void sendError(int sc, String msg) throws IOException {
		if (sc == 404) {
			notFound(req);
		} else {
			super.sendError(sc, msg);
		}
	}

	/**
	 * Sets the request attribute that a 404 was raised.
	 */
	private void notFound(final HttpServletRequest req) {
		req.setAttribute(DefaultJersey.FOUR_O_FOURED, true);
	}

	public void sendError(int sc) throws IOException {
		if (sc == 404) {
			notFound(req);
		} else {
			super.sendError(sc);
		}
	}

	public void setStatus(int sc, String sm) {
		if (sc == 404) {
			notFound(req);
		} else {
			super.setStatus(sc, sm);
		}
	}

	public void setStatus(int sc) {
		if (sc == 404) {
			notFound(req);
		} else {
			super.setStatus(sc);
		}
	}
}