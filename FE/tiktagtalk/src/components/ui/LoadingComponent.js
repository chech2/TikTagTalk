import "./LoadingComponent.css"
import 'animate.css';

function LoadingComponent() {

    return (
        <div className="loading-container">
            <div className="loading-logo-container">
                <img src="/TikTagTalk_logo.png" className="loading-logo animate__animated animate__bounce animate__slow animate__infinite"></img>
            </div>
        </div>
    )
}

export default LoadingComponent;