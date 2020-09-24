import React from "react";

class Square extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      bgColor: "white",
      padding: "19px 19px 7px 7px",
      startClicked: false,
      stopClicked: false,
      fieldType: "EMPTY",
      squareID: this.props.squareID
    };
  }

  //handle the onClick event
  handleClick = e => {
    this.setState({ startClicked: this.props.startClicked });
    var status;
    if (this.props.startClicked) {
      this.setState({
        bgColor: "green",
        fieldType: "START",
        startClicked: false
      });
      status = "START";
    } else if (this.props.stopClicked) {
      this.setState({
        bgColor: "red",
        fieldType: "END",
        startClicked: false
      });
      status = "END";
    } else if (this.state.fieldType !== "EMPTY") {
      this.setState({
        bgColor: "white",
        fieldType: "EMPTY",
        startClicked: false
      });
      status = "EMPTY";
    } else {
      this.setState({
        bgColor: "black",
        fieldType: "WALL",
        startClicked: false
      });
      status = "WALL";
    }
    this.props.parentCallback(status, this.state.squareID);
  };

  render() {
    return (
      <button
        style={{
          backgroundColor: this.state.bgColor,
          padding: this.state.padding
        }}
        onClick={this.handleClick}
        className="square"
      >
        {}
      </button>
    );
  }
}

export default Square;
