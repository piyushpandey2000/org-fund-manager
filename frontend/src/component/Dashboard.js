import '../style/Dashboard.css'
import { useEffect, useState } from 'react';

const Dashboard = () => {
    const API_URL = 'http://localhost:8080/foundation/info'

    const [ dashInfo, setDashInfo ] = useState({});

    useEffect(() => {
        const fetchInfo = async () => {
            try {
                const response = await fetch(API_URL);
                const info = await response.json();
                console.log(JSON.stringify(info));
                setDashInfo(info);
            } catch (e) {
                console.error(e.stack)
            }
        }

        fetchInfo();
    }, []);

    return (
        <div className='main'>
            <div className='infoBlock'>
                <h1>{dashInfo.name}</h1>
                <div className='infoBlockStats'>
                    <div className='fundStats'>
                        <h2>Current fund: {dashInfo.currFund}</h2>
                        <h2>Transferred fund: {dashInfo.fundTransferred}</h2>
                        <h2>Number of transfers: {dashInfo.numTransfers}</h2>
                        <h2>Avg. transfer value: {dashInfo.numTransfers === 0 ? 0 : dashInfo.fundTransferred/dashInfo.numTransfers}</h2>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;