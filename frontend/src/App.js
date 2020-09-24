import React from "react";
import "./App.css";
import Board from "./components/Board";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: "",
      fetched: false
    };
    this.board = React.createRef();
    this.maze = {
      maze: [],
      sizeX: 10,
      sizeY: 10,
      start: 0,
      end: 0
    };
  }

  fetchSolution(json) {
    console.log(json);
    fetch("http://localhost:8080/maze", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: json
    })
      .then(response => response.json())

      .then(data =>
        this.setState({
          solution: data.solution,
          history: data.history,
          runtime: data.runtime,
          solved: data.solved,
          fetched: true
        })
      );
  }

  renderHistory(history) {
    var result = [];
    for (var i = 0; i < history.length; i++) {
      result.push(
        <p key={i}>
          {history[i][0]} : {history[i][1]}
        </p>
      );
    }
    return result;
  }

  renderSolution() {
    if (this.state.fetched) {
      return (
        <div>
          <div>
            <h2>Solved</h2>
            <p>{this.state.solved.toString()}</p>
          </div>
          <div>
            <h2>Runtime</h2>
            <p>{this.state.runtime}</p>
          </div>
          <div>
            <h2>Solution</h2>
            <p>{this.state.solution.join(", ")}</p>
          </div>
          <div>
            <h2>History</h2>
            <div className="History">
              {this.renderHistory(this.state.history)}
            </div>
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <p>No Results</p>
        </div>
      );
    }
  }
  // Handles the submit-button-click

  handleSubmit = event => {
    var stopFound = false;
    var startFound = false;
    this.maze = this.board.current.maze;
    console.log(this.maze);
    for (var idx = 0; idx < this.maze.sizeX * this.maze.sizeY; ++idx) {
      if (this.maze.maze[idx] === "START") {
        if (startFound) {
          alert("Too many START blocks");
          return;
        } else {
          startFound = true;
        }
      }
      if (this.maze.maze[idx] === "END") {
        if (stopFound) {
          alert("Too many STOP blocks");
          return;
        } else {
          stopFound = true;
        }
      }
    }
    console.log(this.maze.maze);

    if (!startFound || !stopFound) {
      alert("Missing START or STOP block");
      return;
    }

    console.log(this.maze);

    this.fetchSolution(JSON.stringify(this.maze));

    event.preventDefault();
  };

  handleChange = event => {
    this.setState({ value: event.target.value });
  };

  // Click handler for maze x-size change
  handleClickSizeX = event => {
    //set size when value is greater than 1, otherwise use default size
    if (event.target.value > 1) {
      this.setState({ value: event.target.value });
      this.maze.sizeX = parseInt(this.state.value, 10);
    } else {
      this.setState({ value: 10 });
      this.maze.sizeX = 10;
    }
  };

  // Keydown handler for maze x-size change
  handleKeyDownSizeX = event => {
    if (event.key === "Enter") {
      this.handleClickSizeX(event);
    }
  };

  // Click handler for maze y-size change
  handleClickSizeY = event => {
    //set size when value is greater than 1, otherwise use default size
    if (event.target.value > 1) {
      this.setState({ value: event.target.value });
      this.maze.sizeY = parseInt(this.state.value, 10);
    } else {
      this.setState({ value: 10 });
      this.maze.sizeY = 10;
    }
  };

  // Keydown handler for maze x-size change
  handleKeyDownSizeY = event => {
    if (event.key === "Enter") {
      this.handleClickSizeY(event);
    }
  };

  handleClickStart = event => {
    console.log("Select start square...");
    this.board.current.setState({ startClicked: true });
    this.board.current.setState({ stopClicked: false });
    this.render();
  };

  handleClickStop = event => {
    console.log("Select end square...");
    this.board.current.setState({ startClicked: false });
    this.board.current.setState({ stopClicked: true });
    this.render();
  };

  // Render-Method
  render() {
    return (
      <div className="App">
        <div>
          <span>
            <input
              placeholder="Maze height"
              onBlur={this.handleClickSizeX}
              onKeyDown={this.handleKeyDownSizeX}
              onChange={this.handleChange}
            />
          </span>
          <span>
            <input
              placeholder="Maze width"
              onBlur={this.handleClickSizeY}
              onKeyDown={this.handleKeyDownSizeY}
              onChange={this.handleChange}
            />
          </span>
        </div>
        <br />
        <Board
          ref={this.board}
          sizex={this.maze.sizeX}
          sizey={this.maze.sizeY}
        />
        <div>
          <span>
            <button onClick={this.handleClickStart}>SET START</button>
          </span>
          <span>
            <button onClick={this.handleClickStop}>SET END</button>
          </span>
        </div>
        <div>
          <button onClick={this.handleSubmit}>Get Solution</button>
        </div>
        <div>{this.renderSolution()}</div>
      </div>
    );
  }
}
export default App;
