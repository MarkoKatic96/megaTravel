import React from 'react'
import './MainPanel.css'
import NonActivatedUsers from './user/NonActivatedUsers';
import BlockedUsers from './user/BlockedUsers';
import ActivatedUsers from './user/ActivatedUsers';

const UserPanel = () => {
    return (
        <div className="side">
            <NonActivatedUsers />
            <ActivatedUsers />
            <BlockedUsers />
        </div>
    );
}

export default UserPanel;