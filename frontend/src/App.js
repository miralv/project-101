import React, { Component } from 'react';
import CompanyListPage from './pages/CompanyListPage';
import EmployeeListPage from './pages/EmployeeListPage';
import NavigationAppBar from './components/NavigationAppBar';
import { Route, Switch } from 'react-router-dom';
import NotFoundPage from './pages/NotFoundPage';
import './styles/styles.css';
import FrontPage from './pages/FrontPage';
import { UncontrolledAlert } from 'reactstrap';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            unhandledPromiseRejections: []
        };
    }

    componentDidMount() {
        window.onunhandledrejection = (err) => {
            this.setState((state) => {
                const unhandledPromiseRejections = [ ...state.unhandledPromiseRejections, err.reason ];

                return {
                    unhandledPromiseRejections
                };
            });
        };
    }

    render() {
        const unhandledPromiseRejections = this.state.unhandledPromiseRejections;
        let alerts = [];
        unhandledPromiseRejections.map((rejection, index) => {
            return alerts.push(
                <UncontrolledAlert key={index} color="danger">
                    <h4 className="alert-heading">{rejection.message}</h4>
                    <hr />
                    <p className="mb-0">{rejection.response ? rejection.response.data.message : ''}</p>
                </UncontrolledAlert>
            );
        });

        return (
            <>
                <NavigationAppBar />
                <main role="main" className="container">
                    {alerts}
                    <Switch>
                        <Route exact path="/" component={FrontPage} />
                        <Route path="/companies" component={CompanyListPage} />
                        <Route path="/employees" component={EmployeeListPage}/>
                        <Route component={NotFoundPage} />
                    </Switch>
                </main>
            </>
        );
    }
}

export default App;
