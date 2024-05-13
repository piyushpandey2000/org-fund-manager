import '../style/SendEmail.css'
import { useState } from "react";

const SendEmail = () => {
    const API_URL_ADD = 'http://localhost:8080/nonprofit/addFund'

    const [ id, setId ] = useState(0);
    const [ fundAmount, setFundAmount ] = useState(0);
    const [ emailContent, setEmailContent ] = useState('');

    const handleSubmit = () => {
        const addFund = async () => {
            try {
                console.log(id);
                console.log(typeof(id));
                console.log(fundAmount);
                console.log(typeof(fundAmount));

                await fetch(API_URL_ADD,
                    {
                        method: 'POST', 
                        body: JSON.stringify({
                            'id': id,
                            'fund': fundAmount,
                            'emailContent': emailContent
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
        
        if (id >0 && fundAmount > 0 && emailContent !== '') {
            addFund();
        }
    }

    const handleClear = () => {
        setId(0);
        setFundAmount(0);
        setEmailContent('');
    }

    return (
        <div className='send-email-main'>
            <form>
                <label>Non-profit id</label>
                <input type='number' onChange={(e) => setId(parseInt(e.target.value))}></input>
                <label>Transfer amount</label>
                <input type='number' onChange={(e) => setFundAmount(parseInt(e.target.value))}></input>
                <label>Compose Email</label>
                <textarea 
                    onChange={(e) => setEmailContent(e.target.value)}
                    placeholder='Use $name$, $email$, $address$, $amount$'
                ></textarea>
                <button type='submit' onClick={handleSubmit}>Submit</button>
                <button type='reset' onClick={handleClear}>Clear</button>
            </form>
        </div>
    );
}

export default SendEmail;