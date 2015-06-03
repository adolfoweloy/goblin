package br.com.goblin.listener;

import java.util.regex.Pattern;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AuthorizationListener implements PhaseListener {
	private static final long serialVersionUID = 1L;
	private static final String PROTECTED_URL = "^/account/.*";
	
	@Override
	public void afterPhase(PhaseEvent evt) {
		FacesContext context = evt.getFacesContext();
		if (context.getViewRoot() != null) {
			String viewId = context.getViewRoot().getViewId();
			
			if (Pattern.matches(PROTECTED_URL, viewId)) {
				Object user = context.getExternalContext().getSessionMap().get("user");
				
				if (user == null) {
					NavigationHandler handler = context.getApplication().getNavigationHandler();
					handler.handleNavigation(context, null, "login");
				}
				
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent evt) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
