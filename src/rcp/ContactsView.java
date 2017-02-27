package rcp;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import rcp.model.Contact;
import rcp.model.ContactsEntry;
import rcp.model.ContactsGroup;
import rcp.model.IContactsListener;
import rcp.model.Session;

public class ContactsView extends ViewPart {

	public static final String ID = "rcp.ContactsView"; //$NON-NLS-1$
	private TreeViewer treeViewer;
	private Session session;
	private IAdapterFactory adapterFactory = new HyperbolaAdapterFactory();
	public ContactsView() {
		super();
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		//Composite container = new Composite(parent, SWT.NONE);
		//��ʱ��������һ����ģʽ
		initSession();
		
		treeViewer = new TreeViewer(parent, SWT.BORDER| SWT.MULTI| SWT.V_SCROLL);
		Platform.getAdapterManager().registerAdapters(adapterFactory, Contact.class);
		getSite().setSelectionProvider(treeViewer);
		
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		treeViewer.setContentProvider(new BaseWorkbenchContentProvider());
		treeViewer.setInput(session.getRoot());
		session.getRoot().addContactsListener(new IContactsListener() {
			
			@Override
			public void contactsChanged(ContactsGroup contacts, ContactsEntry entry) {
				treeViewer.refresh();
				
			}
		});
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}
	
	

	@Override
	public void setFocus() {
		treeViewer.getControl().getFont();
	}
	
	private void initSession() {
		session = new Session();
		ContactsGroup root = session.getRoot();
		ContactsGroup friendsGroup = new ContactsGroup(root, "Friends");
		root.addEntry(friendsGroup);
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Logan", "�޸�", "local"));
		friendsGroup.addEntry(new ContactsEntry(friendsGroup, "Lina", "����", "local"));
		ContactsGroup otherGroup = new ContactsGroup(root, "other");
		root.addEntry(otherGroup);
		otherGroup.addEntry(new ContactsEntry(otherGroup, "mis", "��˹", "local"));
		
	}

	@Override
	public void dispose() {
		Platform.getAdapterManager().unregisterAdapters(adapterFactory);
		super.dispose();
	}

}
