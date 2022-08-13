import React from "react";
import { Menu, Segment } from "semantic-ui-react";

export default function Navi() {

  return (

    <div>
      <Segment inverted>
        <Menu inverted pointing secondary>
          <Menu.Item name="home"/>

          <Menu.Item name="messages" />

          <Menu.Item name="friends"/>

        </Menu>
      </Segment>
    </div>

  );
}
