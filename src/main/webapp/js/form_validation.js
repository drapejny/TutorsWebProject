document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('app_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault()

        const inputs = document.querySelectorAll('.form_input_container')
        let errors = 0;
        inputs.forEach(el => {
            el.classList.remove('_error')
            if (el.classList.contains('subjects')) {
                let checks = 0
                el.querySelectorAll('input[type=checkbox]').forEach(c => {
                    if (c.checked) {
                        checks++
                    }
                })
                if (!checks || checks > 8) {
                    errors++
                    el.classList.add("_error")
                }
            }
            if (el.classList.contains('phone')) {
                if (!/^\+375[0-9]{9}$/.test(el.querySelector('input').value)) {
                    errors++
                    el.classList.add("_error")
                }
            }
            if (el.classList.contains('city')) {
                if (!/[А-яЁё`'.-]{1,32}$/.test(el.querySelector('input').value)) {
                    errors++
                    el.classList.add("_error")
                }
            }
            if (el.classList.contains('education')) {
                if (!/^(.|\n){1,300}$/.test(el.querySelector('textarea').value)) {
                    errors++
                    el.classList.add("_error")
                }
            }
            if (el.classList.contains('info')) {
                if (!/^(.|\n){1,500}$/.test(el.querySelector('textarea').value)) {
                    errors++
                    el.classList.add("_error")
                }
            }
            if (el.classList.contains('price')) {
                if (!/^[1-9][0-9]{0,2}$/.test(el.querySelector('input').value)) {
                    errors++
                    el.classList.add("_error")
                }
            }
        })
        if (!errors) {
            form.submit()
        }
    })
})

