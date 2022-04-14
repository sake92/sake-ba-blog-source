



document.querySelectorAll('.flowrun-instance').forEach(mountElem => {
    loadEditor()
})



function loadEditor() {
    const colorScheme = FlowrunColorSchemeObj.default.withFontName("Fira Mono");
    const mountElem = document.querySelector(".flowrun-instance");
    const editable = mountElem.classList.contains("flowrun--editable");
    const mountCB = () => {
        mountElem.classList.remove("flowrun--hidden");
    }
    FlowRun(mountElem, colorScheme, editable, null, mountCB, null);
}


