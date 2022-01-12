import React from 'react';
import { CDBSidebar, CDBSidebarContent, CDBSidebarFooter, CDBSidebarHeader, CDBSidebarMenu, CDBSidebarMenuItem } from 'cdbreact';
import { NavLink, useRouteMatch } from 'react-router-dom';
import './assignmentstyle.css';

const Sidebar = () => {
  const { url } = useRouteMatch();
  return (
    <div className="side" style={{ display: 'flex', height: '100vh', overflow: 'scroll initial' }}>
      <CDBSidebar textColor="#333" backgroundColor="#fff">
        <CDBSidebarHeader prefix={<i className="fa fa-bars fa-large"></i>}>
          <a href="/assignment" className="text-decoration-none" style={{ color: '#333' }}>
            CRNS
          </a>
        </CDBSidebarHeader>

        <CDBSidebarContent className="sidebar-content">
          <CDBSidebarMenu>
            <NavLink exact to={`${url}/assignment1`} activeClassName="activeLink">
              <CDBSidebarMenuItem className="navlink">Assignment 1</CDBSidebarMenuItem>
            </NavLink>
            <NavLink exact to={`${url}/assignment2`} activeClassName="activeLink">
              <CDBSidebarMenuItem className="navlink">Assignment 2</CDBSidebarMenuItem>
            </NavLink>
            <NavLink exact to={`${url}/assignment3`} activeClassName="activeLink">
              <CDBSidebarMenuItem className="navlink">Assignment 3</CDBSidebarMenuItem>
            </NavLink>
            <NavLink exact to={`${url}/assignment4`} activeClassName="activeLink">
              <CDBSidebarMenuItem className="navlink">Assignment 4</CDBSidebarMenuItem>
            </NavLink>
          </CDBSidebarMenu>
        </CDBSidebarContent>

        <CDBSidebarFooter style={{ textAlign: 'center' }}></CDBSidebarFooter>
      </CDBSidebar>
    </div>
  );
};

export default Sidebar;
