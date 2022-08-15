import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Menu, Segment } from "semantic-ui-react";
import CategoryService from "../services/CategoryService";

export default function Navi() {

  let categoryService = new CategoryService();

  const [categories, setCategories] = useState([]);
 
  useEffect(() => {
   categoryService.getCategories().then((result) => setCategories(result.data));
  }, [])
 

  return (

    <div>
      <Segment inverted>
        <Menu inverted pointing secondary>
          <Link to='/'><Menu.Item name="Home"/></Link>

          <Menu.Item name="messages" />

          <Menu.Item name="friends"/>
          
        </Menu>

   
      </Segment>
    </div>

  );
}
