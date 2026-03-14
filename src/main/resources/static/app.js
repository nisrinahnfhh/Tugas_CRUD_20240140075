$(document).ready(function() {
    loadData();

    $('#ktpForm').submit(function(e) {
        e.preventDefault();

        let id = $('#idKtp').val();
        let payload = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        let method = id ? 'PUT' : 'POST';
        let url = id ? `/ktp/${id}` : '/ktp';

        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(payload),
            success: function(response) {
                showAlert('success', 'Data berhasil disimpan!');
                resetForm();
                loadData();
                $('#ktpModal').modal('hide');
            },
            error: function(xhr) {
                showAlert('danger', xhr.responseJSON.status || 'Terjadi kesalahan!');
            }
        });
    });
});

function loadData() {
    $.ajax({
        url: '/ktp',
        type: 'GET',
        success: function(response) {
            let tbody = $('#tableBody');
            tbody.empty();
            response.data.forEach(function(item) {
                tbody.append(`
                    <tr>
                        <td>${item.nomorKtp}</td>
                        <td>${item.namaLengkap}</td>
                        <td>${item.alamat}</td>
                        <td>${item.tanggalLahir}</td>
                        <td>${item.jenisKelamin}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="editData(${item.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteData(${item.id})">Hapus</button>
                        </td>
                    </tr>
                `);
            });
        }
    });
}

function editData(id) {
    $.ajax({
        url: `/ktp/${id}`,
        type: 'GET',
        success: function(response) {
            let data = response.data;
            $('#idKtp').val(data.id);
            $('#nomorKtp').val(data.nomorKtp);
            $('#namaLengkap').val(data.namaLengkap);
            $('#alamat').val(data.alamat);
            $('#tanggalLahir').val(data.tanggalLahir);
            $('#jenisKelamin').val(data.jenisKelamin);
            $('#btnSave').text('Update');
            $('#ktpModalLabel').html('<i class="bi bi-pencil-square me-2"></i>Edit Data KTP');
            $('#ktpModal').modal('show');
        }
    });
    // $.ajax({
    //     url: 'http://localhost:8080/ktp/' + id,
    //     type: 'GET',
    //     success: function(response) {
    //         // ... isi form dengan response data ...
    //         $('#idKtp').val(response.id);
    //         $('#nomorKtp').val(response.nomorKtp);
    //         // ... dst ...
    //
    //         // Ubah judul modal dan tampilkan pop-up nya
    //         $('#ktpModalLabel').html('<i class="bi bi-pencil-square me-2"></i>Edit Data KTP');
    //         $('#ktpModal').modal('show'); // <-- PERINTAH UNTUK MEMBUKA MODAL
    //     }
    // });
}

function deleteData(id) {
    if(confirm('Yakin ingin menghapus data ini?')) {
        $.ajax({
            url: `/ktp/${id}`,
            type: 'DELETE',
            success: function() {
                showAlert('success', 'Data berhasil dihapus!');
                loadData();
            }
        });
    }
}

function resetForm() {
    $('#idKtp').val('');
    $('#ktpForm')[0].reset();
    $('#btnSave').text('Simpan');
}

function showAlert(type, message) {
    let alertBox = $('#alert-box');
    alertBox.removeClass('d-none alert-success alert-danger').addClass(`alert-${type}`).text(message);
    setTimeout(() => alertBox.addClass('d-none'), 3000);
}

// Panggil fungsi ini di HTML saat tombol "Tambah Data KTP" diklik
function prepareAdd() {
    $('#ktpForm')[0].reset();
    $('#idKtp').val('');
    $('#ktpModalLabel').html('<i class="bi bi-person-plus me-2"></i>Tambah Data Baru');
}