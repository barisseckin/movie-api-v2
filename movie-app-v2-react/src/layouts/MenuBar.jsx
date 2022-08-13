import React from "react";
import { Dropdown, Grid, GridColumn, GridRow, Menu } from "semantic-ui-react";

export default function MenuBar() {

  return (
    
    <div>

      <Menu secondary vertical>
        <Menu.Item name="account" />
        <Menu.Item name="settings" />
        <Dropdown item text="Display Options">
          <Dropdown.Menu>
            <Dropdown.Header>Text Size</Dropdown.Header>
            <Dropdown.Item>Small</Dropdown.Item>
            <Dropdown.Item>Medium</Dropdown.Item>
            <Dropdown.Item>Large</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Menu>

    </div>
  );
}
