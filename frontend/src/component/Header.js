import '../style/Header.css'
import { useState } from 'react'
import { Link } from 'react-router-dom';

const Header = () => {

    const [navList, setNavList] = useState([
        {id: 0, text: 'Dashboard', link: '/', active: true},
        {id: 1, text: 'Non-Profits', link: '/non-profits', active: false},
        {id: 2, text: 'View Email', link: '/view-email', active: false},
        {id: 3, text: 'Send Email', link: '/send-email', active: false}
    ]);

    const handleSelect = (id) => {
        const updatedList = navList.map((entry) => id === entry.id ? {...entry, active: true} : {...entry, active: false});
        setNavList(updatedList);
    }

    return (
        <nav className='header-main'>
            <label>
                    Foundation Fund Manager
            </label>
            <ul>
                {navList.map((entry) => (
                    <li>
                        <Link
                            key={entry.id}
                            className={(entry.active ? 'active': '') + ' header-text'}
                            to={entry.link}
                            onClick={() => handleSelect(entry.id)}
                        >
                            {entry.text}
                        </Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
}

export default Header;