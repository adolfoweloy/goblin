package br.com.goblin.web.dialog;

import javax.faces.bean.ManagedBean;

import br.com.goblin.web.faces.FacesUtils;

/**
 * This class, is an example of slash scope usage.
 * When working with primefaces, you do not need to create a dedicated page
 * just to ask users for confirmations. With frameworks such as primefaces, developers should use modal dialogs
 * instead of make users wait for submissions between pages belonging to confirmation flow.
 * 
 * @author aeloy
 */
@ManagedBean
public class ConfirmationBean {

	private static final String CONFIRM_OUTCOME = "confirmation";
	
	public static final String BEAN_NAME = "confirmationBean";
	
	/**
	 * Returns the message, user must see on confirmation dialog
	 * @return
	 */
	public String getMessage() {
		FacesUtils.addFlashParam("confirm", FacesUtils.getFlashParam("confirm"));
		FacesUtils.addFlashParam("cancel", FacesUtils.getFlashParam("cancel"));
		return FacesUtils.getFlashParam("message");
	}

	/**
	 * configure the message to show in confirmation dialog
	 * @param message
	 */
	public void setMessage(String message) {
		FacesUtils.addFlashParam("message", message);
	}

	/**
	 * configure the action to be executed when user select YES on confirmation dialog.
	 * @param confirm
	 */
	public void setConfirm(ConfirmationAction confirm) {
		FacesUtils.addFlashParam("confirm", confirm);
	}
	
	/**
	 * configure the action to be executed when user select NO on confirmation dialog.
	 * @param confirm
	 */
	public void setCancel(ConfirmationAction cancel) {
		FacesUtils.addFlashParam("cancel", cancel);
	}
	
	/**
	 * Execute the confirmation action.
	 * @return
	 */
	public String executeConfirm() {
		ConfirmationAction action = FacesUtils.getFlashParam("confirm");
		return FacesUtils.executeAction(action.getBeanName(), action.getMethodName(), action.getParameters());
	}
	
	/**
	 * execute the cancel action.
	 * @return
	 */
	public String executeCancel() {
		ConfirmationAction action = FacesUtils.getFlashParam("cancel");
		return FacesUtils.executeAction(action.getBeanName(), action.getMethodName());
	}
	
	/**
	 * Defines data structure needed to pass through navigation via flash scope.
	 * @author aeloy
	 */
	public static class ConfirmationAction {
		private String beanName;
		private String methodName;
		private Object[] parameters;
		
		public ConfirmationAction(String beanName, String methodName, Object[] parameters) {
			super();
			this.beanName = beanName;
			this.methodName = methodName;
			this.parameters = parameters;
		}

		public String getBeanName() {
			return beanName;
		}
		public String getMethodName() {
			return methodName;
		}
		public Object[] getParameters() {
			return parameters;
		}
	}

	/**
	 * go to the confirmation page to ask user for a choice.
	 * @return String - the outcome to message confirmation dialog.
	 */
	public String showMessage() {
		return CONFIRM_OUTCOME;
	}
}
