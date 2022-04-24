



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
    const programJson = `{"id":"id_fbf74f93_612b_46bf_b7a3_8584d96bec20","name":"Program","config":{"lang":"java","showFunctions":true,"showGenCode":true},"main":{"rawId":"main","name":"main","parameters":[],"tpe":"Void","statements":[{"id":"id_671f2537_441d_41b3_aad6_ab240ed45c92","@type":"Begin"},{"id":"id_aeaa760c_0fba_496c_8ca1_33e5af707e65","maybeValue":null,"@type":"Return"}]},"functions":[],"version":"0.1"}`;
    FlowRun(mountElem, colorScheme, editable, programJson, mountCB, null);
}


