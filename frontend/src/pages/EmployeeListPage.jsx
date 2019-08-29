import React, { Component } from 'react';
import DeleteButton from '../containers/DeleteButton';
import { Button, Card, CardBody, CardText, CardTitle, Table } from 'reactstrap';
import { FaBuilding, FaSyncAlt } from 'react-icons/fa';
import EmployeesApi from "../services/EmployeesApi";
import CreateCompanyModal from "../containers/CreateCompanyModal";


class EmployeeListPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        };

        this.apiReadAllEmployees = this.apiReadAllEmployees.bind(this);
    }

    componentDidMount() {
        this.apiReadAllEmployees();
    }

    async apiReadAllEmployees() {
        const employees = await EmployeesApi.readAllEmployees();
        this.setState({employees: employees});
    }

    render() {
        const employees = this.state.employees;
        let employeesRows = [];

        employees.map((employee) => {
            return employeesRows.push(
                <tr key={employee.id}>
                </tr>
            );
        });

        const employeesTable = (
        <Table dark striped>
            <thead>
            <tr>
                <th scope="col"Id> </th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Date Of Birth</th>
                <th scope="col">Company</th>
                <th scope="col"> Actions</th>
            </tr>
            </thead>
            <tbody>
                {employeesRows}
            </tbody>
        </Table>
    );

        const emptyTable = (
            <p>No employees yet.</p>
        )
        return (
            <Card color="black">
                <CardBody>
                    <CardTitle tag="h3"><FaBuilding /> List of employees</CardTitle>
                    <div className="card-action">
                        <Button color="secondary" onClick={this.apiReadAllEmployees}><FaSyncAlt /></Button> {' '}
                    </div>
                    <CardText tag="div">
                        {employees.length > 0 ? employeesTable : emptyTable}
                    </CardText>
                </CardBody>
            </Card>
            );
    }

}



export default EmployeeListPage;
