package org.phoebus.channel.views.ui;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;

import org.phoebus.channelfinder.Tag;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.phoebus.framework.preferences.PhoebusPreferenceService;
import org.phoebus.ui.dialog.DialogHelper;
import org.phoebus.ui.dialog.MultiLineInputDialog;

import static org.phoebus.channel.views.ui.ChannelFinderController.logger;
/**
 * A dialog for adding a tag to a list of channels
 * 
 * @author Kunal Shroff
 *
 */
public class AddTagDialog extends Dialog<Tag> {

    public AddTagDialog(final Node parent, final Collection<String> tags) {
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResizable(true);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("SelectEntity.fxml"));
        try {
            getDialogPane().setContent(loader.load());
            SelectEntityController controller = loader.getController();
            controller.setAvaibleOptions(tags);
            setResultConverter(button -> {
                return button == ButtonType.OK
                        ? Tag.Builder.tag(controller.getSelectedOption()).build()
                        : null;
            });
            DialogHelper.positionDialog(this, parent, -250, -400);
        } catch (IOException e) {
            // TODO update the dialog
            logger.log(Level.WARNING, "Failed to add tag", e);
        }
    }
}
