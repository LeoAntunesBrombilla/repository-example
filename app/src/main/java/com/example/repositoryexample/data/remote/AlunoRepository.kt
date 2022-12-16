package com.example.repositoryexample.data.remote

class AlunoRepository : IAlunoRepository {
    override fun getAlunos(): List<String> {
        return listOf("0000-000", "0000-001", "0000-002")
    }
}