package rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//layout.addStandaloneView(FirstView.ID, true, layout.LEFT, 1.0f, layout.getEditorArea());
		//layout.addView(FirstView.ID, layout.LEFT, 1.0f, layout.getEditorArea());
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(ContactsView.ID, false, IPageLayout.LEFT,
				1.0f, layout.getEditorArea());
	}
}
