import React, {Component} from "react";
import logo from "../../logo.svg";
import {animateScroll as scroll, Link} from "react-scroll";
import './Navigation.css';

export default class NavbarComponent extends Component {
    scrollToTop = () => {
        scroll.scrollToTop();
    };

    render() {
        return (
            <nav className="nav" id="navbar">
                <div className="nav-content">
                    {/*<img*/}
                    {/*    src={logo}*/}
                    {/*    className="nav-logo"*/}
                    {/*    alt="Logo"*/}
                    {/*    onClick={this.scrollToTop}*/}
                    {/*/>*/}
                    <ul className="nav-items">
                        <li className="nav-item">
                            <Link
                                activeClass="active"
                                to="section1"
                                spy={true}
                                smooth={true}
                                offset={-70}
                                duration={500}
                            >
                                Filtry
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link
                                activeClass="active"
                                to="section2"
                                spy={true}
                                smooth={true}
                                offset={-70}
                                duration={500}
                            >
                                Wyszukane Pakiety
                            </Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}