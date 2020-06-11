import React, {Component} from "react";
import {animateScroll as scroll, Link} from "react-scroll";
import './Navigation.css';
import {FILTERS, SEARCHED_PACKAGES} from "../../lang/pl";

export default class NavbarComponent extends Component {
    scrollToTop = () => {
        scroll.scrollToTop();
    };

    render() {
        return (
            <nav className="nav" id="navbar">
                <div className="nav-content">
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
                                {FILTERS}
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
                                {SEARCHED_PACKAGES}
                            </Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}