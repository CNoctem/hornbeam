package com.latte.hb.view.tools;

import com.latte.hb.view.tools.search.SearchTab;
import com.latte.hb.view.tools.settings.SettingsTab;

import javax.swing.*;

public class ToolBox extends JTabbedPane {

    public ToolBox() {
        addTab("Search", new SearchTab());
        addTab("Settings", new SettingsTab());
    }

}
