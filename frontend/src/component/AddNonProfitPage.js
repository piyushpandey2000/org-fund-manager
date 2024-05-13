import '../style/AddNonProfitPage.css'
import { useState } from "react";

const AddNonProfitPage = () => {
    const API_URL_ADD = 'http://localhost:8080/nonprofit/add'

    const [ name, setName ] = useState('');
    const [ email, setEmail ] = useState('');
    const [ address, setAddress ] = useState('');

    const handleSubmit = () => {
        const addNonProfit = async () => {
            try {
                await fetch(API_URL_ADD,
                    {
                        method: 'POST', 
                        body: JSON.stringify({
                            'name': name,
                            'email': email,
                            'address': address
                        }),
                        headers: {
                            'content-type': 'application/json'
                          }
                    }
                );
                handleClear();
            } catch (e) {
                console.error(e.stack)
            }
        }
        
        if (name !== '' && email !== '' && address !== '') {
            addNonProfit();
        }
    }

    const handleClear = () => {
        setName('');
        setEmail('');
        setAddress('');
    }

    return (
        <div className='add-non-profit-main'>
            <form>
                <label>Name</label>
                <input required value={name} onChange={(e) => setName(e.target.value)}></input>
                <label>Email</label>
                <input type='email' required value={email} onChange={(e) => setEmail(e.target.value)}></input>
                <label>Address</label>
                <input required value={address} onChange={(e) => setAddress(e.target.value)}></input>
                <button type='submit' onClick={handleSubmit}>Submit</button>
                <button type='reset' onClick={handleClear}>Clear</button>
            </form>
        </div>
    );
}

export default AddNonProfitPage;