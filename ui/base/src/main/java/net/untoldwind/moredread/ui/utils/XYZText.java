package net.untoldwind.moredread.ui.utils;

import static net.untoldwind.moredread.ui.utils.FormatUtils.formatLength;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jme.math.Vector3f;

public class XYZText extends Composite {

	Text xText;
	Text yText;
	Text zText;

	public XYZText(final Composite parent) {
		super(parent, SWT.NONE);

		final Display display = parent.getDisplay();
		final GridLayout layout = new GridLayout(3, true);
		layout.marginHeight = 1;
		layout.marginWidth = 1;
		layout.horizontalSpacing = 1;
		layout.verticalSpacing = 1;
		setLayout(layout);
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		setBackground(display.getSystemColor(SWT.COLOR_BLACK));

		xText = new Text(this, SWT.FLAT | SWT.SINGLE);
		xText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		xText.setBackground(display.getSystemColor(SWT.COLOR_RED));

		yText = new Text(this, SWT.FLAT | SWT.SINGLE);
		yText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yText.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		zText = new Text(this, SWT.FLAT | SWT.SINGLE);
		zText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		zText.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
	}

	public void setValue(final Vector3f value) {
		if (value == null) {
			xText.setText("");
			yText.setText("");
			zText.setText("");
		} else {
			xText.setText(formatLength(value.x));
			yText.setText(formatLength(value.y));
			zText.setText(formatLength(value.z));
		}
	}
}
