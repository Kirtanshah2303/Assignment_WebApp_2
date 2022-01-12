import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// export const BrandIcon = props => (
//   <div {...props} className="brand-icon">
//     <img src="content/images/logo-jhipster.png" alt="Logo" />
//   </div>
// );

export const Brand = () => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    {/*<BrandIcon />*/}
    <span className="brand-title">Assignment WebApp</span>
    {/*<span className="navbar-version">{VERSION}</span>*/}
  </NavbarBrand>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>Home</span>
    </NavLink>
  </NavItem>
);

export const Course = () => (
  <NavItem>
    <NavLink tag={Link} to="/course" className="d-flex align-items-center">
      <FontAwesomeIcon icon="book" />
      <span>Course</span>
    </NavLink>
  </NavItem>
);
