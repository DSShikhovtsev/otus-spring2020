import React from 'react'
import ReactDom from "react-dom";

const Header = (props) => (
    <h1>{props.title}</h1>
);

export default class AuthorsPage extends React.Component{
    constructor(props) {
        super(props);
        this.state = {authors: []};

        // this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        // fetch('/authorDelete', {
        //     method: 'post',
        //     headers: {'Content-Type':'application/json'},
        //     body: {
        //         "id": this.author.id
        //     }
        // });
    }
    // constructor() {
    //     super();
    //     this.state = {authors: []};
    // }

    componentDidMount() {
        fetch('/showAuthors')
            .then(response => response.json())
            .then(authors => this.setState({authors}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Authors'}/>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Edit Author</th>
                        <th>Delete Author</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.authors.map((author, i) => (
                            <tr key={i}>
                                <form onSubmit={this.handleSubmit}>
                                    <td>
                                        <label>{author.name}</label>
                                    </td>
                                    <td>
                                        <a href={"/author?" + author.id}>Edit</a>
                                    </td>
                                    <td>
                                        <strong>
                                            <button type={"submit"}>Delete</button>
                                        </strong>
                                    </td>
                                </form>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
}

ReactDom.render(<AuthorsPage />, document.getElementById('startPage'));