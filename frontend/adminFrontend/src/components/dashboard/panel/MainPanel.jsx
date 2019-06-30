import React from 'react'
import { BrowserRouter, Route } from 'react-router-dom';
import AgentPanel from './AgentPanel';
import CommentPanel from './CommentPanel';
import Sidebar from '../sidebar/Sidebar';
import CategorizationPanel from './CategorizationPanel';
import ServicePanel from './ServicePanel';
import ActivatedUsers from './user/ActivatedUsers';
import BlockedUsers from './user/BlockedUsers';
import NonActivatedUsers from './user/NonActivatedUsers';
import TypesPanel from './TypesPanel';

const MainPanel = () => {
  return (
    <BrowserRouter>

      <div className="w3-container">
        <Sidebar />
      </div>

      <div className="w3-row">
        <div className="w3-col w3-container m4 l3">
          <Route path="/agents" component={AgentPanel}/>
          <Route path="/comments" component={CommentPanel} />
          <Route path="/noactusers" component={NonActivatedUsers} />
          <Route path="/actusers" component={ActivatedUsers} />
          <Route path="/blockusers" component={BlockedUsers} />
          <Route path="/types" component={TypesPanel} />
          <Route path="/categories" component={CategorizationPanel} />
          <Route path="/services" component={ServicePanel} />
        </div>
      </div>

    </BrowserRouter>
  );
}

export default MainPanel;